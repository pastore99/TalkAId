package model.service.exercise;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;


public interface SpeechRecognitionInterface {

    /**
     * Effettua il riconoscimento vocale utilizzando i servizi di Azure Speech-to-Text.
     *
     * @param audio InputStream rappresentante l'audio da analizzare.
     * @return Una stringa contenente il testo riconosciuto, o null se non riconosciuto.
     * @throws InterruptedException se il thread viene interrotto durante l'esecuzione.
     * @throws ExecutionException   se si verifica un errore durante l'esecuzione del task.
     * @throws IOException          se si verifica un errore di input/output.
     */
    String azureSTT(InputStream audio) throws InterruptedException, ExecutionException, IOException;

    /**
     * Genera un file temporaneo a partire da un InputStream.
     *
     * @param inputAudio InputStream dell'audio.
     * @return Il percorso del file temporaneo creato.
     * @throws IOException se si verifica un errore di input/output.
     */
    String generateFile(InputStream inputAudio) throws IOException;
}
