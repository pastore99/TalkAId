package model.service.exercise;

import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class SpeechRecognition implements SpeechRecognitionInterface{
    private static final String AZURE_PROPERTIES = "/azure.properties";
    private static String speechKey;
    private static String speechRegion;

    /**
     * Costruttore della classe SpeechRecognition.
     * Carica le chiavi e la regione di Azure Speech-to-Text dal file di configurazione.
     */
    public SpeechRecognition(){
        Properties azureProps = loadProps();
        speechRegion = azureProps.getProperty("azure.region");
        speechKey = azureProps.getProperty("azure.key");
    }


    public String azureSTT(InputStream audio) throws InterruptedException, ExecutionException, IOException {
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(speechKey, speechRegion);
        speechConfig.setSpeechRecognitionLanguage("it-It");

        AudioConfig audioConfig = AudioConfig.fromWavFileInput(generateFile(audio));
        SpeechRecognizer speechRecognizer = new SpeechRecognizer(speechConfig, audioConfig);

        Future<SpeechRecognitionResult> task = speechRecognizer.recognizeOnceAsync();
        SpeechRecognitionResult speechRecognitionResult = task.get();

        String result = null;

        if (speechRecognitionResult.getReason() == ResultReason.RecognizedSpeech) {
            result = speechRecognitionResult.getText();
        }
        else if (speechRecognitionResult.getReason() == ResultReason.NoMatch){
            System.err.println("NOMATCH: Speech could not be recognized.");
        }else if (speechRecognitionResult.getReason() == ResultReason.Canceled) {
            CancellationDetails cancellation = CancellationDetails.fromResult(speechRecognitionResult);
            System.out.println("CANCELED: Reason=" + cancellation.getReason());

            if (cancellation.getReason() == CancellationReason.Error) {
                System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
                System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
                System.out.println("CANCELED: Did you set the speech resource key and region values?");
            }
        }

        return result;
    }

    public String generateFile(InputStream inputAudio) throws IOException {
        File tempFile = createTempFile(inputAudio);
        String outputPath = getOutputPath(tempFile);
        deleteExistingFile(outputPath);
        executeCommand(tempFile, outputPath);

        return outputPath;
    }

    File createTempFile(InputStream inputAudio) throws IOException {
        File tempFile = File.createTempFile("tempAudio", ".opus");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputAudio.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        return tempFile;
    }

    String getOutputPath(File tempFile) {
        Path outputPath = Paths.get(tempFile.getPath()).getParent().resolve("outputJava.wav");
        return outputPath.toString();
    }

    void deleteExistingFile(String path) throws IOException {
        try {
            Files.delete(Paths.get(path));
        } catch (FileNotFoundException e){
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Error deleting the file: " + e.getMessage());
        }
    }

    int executeCommand(File tempFile, String path) throws IOException {
        List<String> command = Arrays.asList(
                "ffmpeg",
                "-i", tempFile.getPath(),
                "-ar", "16000",
                "-ac", "1",
                "-acodec", "pcm_s16le",
                path
        );

        int exitCode = -1;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            exitCode = process.waitFor();
            if(exitCode != 0){
                System.err.println("\nExited with error code : " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return exitCode;
    }

    private Properties loadProps() {
        Properties props = new Properties();
        try (InputStream input = SpeechRecognition.class.getResourceAsStream(AZURE_PROPERTIES)) {
            props.load(input);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return props;
    }
}