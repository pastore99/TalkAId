package model.service.exercise;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.junit.jupiter.api.io.TempDir;
import org.powermock.api.mockito.PowerMockito;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.jupiter.api.*;
import org.mockito.*;
import java.io.*;
import java.nio.file.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SpeechRecognitionTest {

     //TODO da sistemare!!!
    @Test
    void testGenerateFile() throws IOException {
        // Arrange
        byte[] audioData = "This is a test audio data".getBytes();
        InputStream inputStream = new ByteArrayInputStream(audioData);

        SpeechRecognition audioProcessor = new SpeechRecognition();

        // Act
        String outputPath = audioProcessor.generateFile(inputStream);

        // Assert
        assertNotNull(outputPath);
        assertTrue(Files.exists(Path.of(outputPath)));

        // Clean up if needed
        Files.deleteIfExists(Path.of(outputPath));
    }

    @Test
    void testGenerateFileWithEmptyInputStream() throws IOException {
        // Arrange
        InputStream emptyInputStream = new ByteArrayInputStream(new byte[0]);
        SpeechRecognition audioProcessor = new SpeechRecognition();

        // Act
        String outputPath = audioProcessor.generateFile(emptyInputStream);

        // Assert
        assertNotNull(outputPath);
        assertFalse(Files.exists(Path.of(outputPath)));
    }

    //worka
    @Test
    void testGenerateFileWithIOExceptionDuringFileCreation() {
        // Arrange
        InputStream inputStream = new ByteArrayInputStream("Test audio data".getBytes()) {
            @Override
            public int read(byte[] b) throws IOException {
                throw new IOException("Simulated IOException during read");
            }
        };

        SpeechRecognition audioProcessor = new SpeechRecognition();

        // Act & Assert
        assertThrows(IOException.class, () -> audioProcessor.generateFile(inputStream));
    }

    /*@Test
        //TODO sto metodo non funge per riga 93!!!
    void testGenerateFileWithIOExceptionDuringFFmpegExecution() throws IOException {
        // Arrange
        InputStream inputStream = new ByteArrayInputStream("Test audio data".getBytes());
        SpeechRecognition audioProcessor = new SpeechRecognition();

        // Mocking ProcessBuilder to throw IOException during start
        PowerMockito.mockStatic(ProcessBuilder.class);
        when(ProcessBuilder.start()).thenThrow(new IOException("Simulated IOException during FFmpeg execution"));

        // Act
        String outputPath = audioProcessor.generateFile(inputStream);

        // Assert
        assertNotNull(outputPath);
        assertTrue(Files.exists(Path.of(outputPath)));
        // Clean up if needed
        Files.deleteIfExists(Path.of(outputPath));
    }
    */
    /*
    //TODO sta roba che c'è sotto è da buttare, giusto per avere la roba sott'occhio del workflow
    @Mock
    private InputStream mockInput;

    @InjectMocks
    private SpeechRecognition audioProcessor = new SpeechRecognition();

    @BeforeEach
    public void setup() throws IOException {
        mockInput = mock(InputStream.class);
        byte[] buffer = new byte[1024];
        Mockito.when(mockInput.read(buffer)).thenReturn(1024).thenReturn(-1);
    }

    @Test
    public void testCreateTempFile() throws IOException {
        File tempFile = audioProcessor.createTempFile(mockInput);
        assertNotNull(tempFile);
        assertTrue(tempFile.exists());
    }

    @Test
    public void testGetOutputPath() {
        File tempFile = new File("tempAudio.opus");
        String outputPath = audioProcessor.getOutputPath(tempFile);
        assertNotNull(outputPath);
    }

    @Test
    public void testDeleteExistingFile() throws IOException {
        Path outputPath = Paths.get("outputJava.wav");
        Files.createFile(outputPath);
        audioProcessor.deleteExistingFile(outputPath.toString());
        assertFalse(Files.exists(outputPath));
    }

    @Test
    public void testExecuteCommand() throws IOException {
        File tempFile = new File("tempAudio.opus");
        String outputPath = "outputJava.wav";
        audioProcessor.executeCommand(tempFile, outputPath);
        // Add assertions to verify the command execution
    }

    @Test
    public void testGenerateFile() throws IOException {
        String path = audioProcessor.generateFile(mockInput);
        assertFalse(Files.exists(Paths.get(path)));
    }
     */
}
