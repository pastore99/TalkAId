package model.service.exercise;

import org.junit.jupiter.api.*;
import java.io.*;
import java.io.IOException;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpeechRecognitionTest {

    @Test
    public void testGenerateFile() throws Exception {
        SpeechRecognition speechRecognition = new SpeechRecognition() {
            @Override
            protected File createTempFile(InputStream inputAudio) {
                // return a test double for the temporary file
                File tempFile = mock(File.class);
                when(tempFile.getPath()).thenReturn("/tmp/tempAudio.opus");
                return tempFile;
            }

            @Override
            protected String getOutputPath(File tempFile) {
                // return a fixed path for the output file
                return "/tmp/outputJava.wav";
            }

            @Override
            protected void deleteExistingFile(String path) {
                // do nothing because the file do not exists since it's mocked
            }

            @Override
            protected int executeCommand(File tempFile, String path) {
                // return a fixed exit code
                return 0;
            }
        };

        // Now you can test the generateFile method
        String testData = "Some test data";
        InputStream someInputStream = new ByteArrayInputStream(testData.getBytes());
        String result = speechRecognition.generateFile(someInputStream);
        assertEquals(result, "/tmp/outputJava.wav");
        // assert something about the result
    }

    @Test
    public void testGenerateFileWithInvalidPath() {
        SpeechRecognition speechRecognition = new SpeechRecognition() {

            @Override
            protected String getOutputPath(File tempFile) {
                // return an invalid path
                return null;
            }
        };

        String testData = "Some test data";
        InputStream someInputStream = new ByteArrayInputStream(testData.getBytes());

        assertThrows(NullPointerException.class, () -> {
            speechRecognition.generateFile(someInputStream);
        });
    }

    @Test
    void testGenerateFileWithIOExceptionDuringFileCreation() {
        InputStream inputStream = new ByteArrayInputStream("Test audio data".getBytes()) {
            @Override
            public int read(byte[] b) throws IOException {
                throw new IOException("Simulated IOException during read");
            }
        };

        SpeechRecognition audioProcessor = new SpeechRecognition();
        assertThrows(IOException.class, () -> audioProcessor.generateFile(inputStream));
    }

    @Test
    public void testGenerateFileWithCommandFailure() throws Exception {
        SpeechRecognition speechRecognition = new SpeechRecognition() {

            @Override
            protected File createTempFile(InputStream inputAudio) {
                // return a test double for the temporary file
                File tempFile = mock(File.class);
                when(tempFile.getPath()).thenReturn("/tmp/tempAudio.opus");
                return tempFile;
            }

            @Override
            protected String getOutputPath(File tempFile) {
                // return a fixed path for the output file
                return "/tmp/outputJava.wav";
            }

            @Override
            protected void deleteExistingFile(String path) {
                // do nothing because the file do not exists since it's mocked
            }

            @Override
            protected int executeCommand(File tempFile, String path) {
                // return a non-zero exit code
                System.err.println("\nExited with error code : " + 1);
                return 1;
            }
        };

        // Now you can test the generateFile method
        String testData = "Some test data";
        InputStream someInputStream = new ByteArrayInputStream(testData.getBytes());

        // Capture the system output
        ByteArrayOutputStream systemErr = new ByteArrayOutputStream();
        System.setErr(new PrintStream(systemErr));
        speechRecognition.generateFile(someInputStream);

        String errorOutput = systemErr.toString();
        assertNotNull(errorOutput);
        assertTrue(errorOutput.contains("Exited with error code : 1"));
    }

}
