// collect DOMs
const display = document.querySelector('.display')
const controllerWrapper = document.querySelector('.controllers')

const State = ['Initial', 'Record', 'Download']
let stateIndex = 0
let mediaRecorder, chunks = [], audioURL = ''

// mediaRecorder setup for audio
if(navigator.mediaDevices && navigator.mediaDevices.getUserMedia){//controlliamo se il browser supporta le funzionalità di mediaDevice
    console.log('mediaDevices supported..')

    navigator.mediaDevices.getUserMedia({//chiediamo all'utente il permesso di usare il microfono
        audio: true
    }).then(stream => {//se abbiamo il permesso facciamo questo
        mediaRecorder = new MediaRecorder(stream)//classe per la registrazione

        mediaRecorder.ondataavailable = (e) => {//evento che si crea quando ci sono stati catturati dei dati audio
            chunks.push(e.data)
        }

        mediaRecorder.onstop = () => {//evento di stop
            const blob = new Blob(chunks, {'type': 'audio/ogg; codecs=opus'})//creiamo un file binario audio ogg con i dati catturati
            const blobdownload = new Blob(chunks,{'type': 'audio/mp3'})
            chunks = []//sio svuota per le future registrazioni
            audioURL = window.URL.createObjectURL(blob)//creiamo un oggetto url per rappresentare l'audio
            document.querySelector('audio').src = audioURL
            downloadAudio(blobdownload)

        }
    }).catch(error => {//altrimenti per errori o altro questo
        console.log('Following error has occured : ',error)
    })
}else{
    stateIndex = ''
    application(stateIndex)
}

const clearDisplay = () => {
    display.textContent = ''
}

const clearControls = () => {
    controllerWrapper.textContent = ''
}

const record = () => {
    stateIndex = 1
    mediaRecorder.start()
    application(stateIndex)
}

const stopRecording = () => {
    stateIndex = 2
    mediaRecorder.stop()

    application(stateIndex)
}

const downloadAudio = (blob) => {
    const mp3Url = URL.createObjectURL(blob);
    const downloadLink = document.createElement('a');
    downloadLink.href = mp3Url;
    downloadLink.download = 'registrazione_audio.mp3';
    downloadLink.textContent = 'Scarica la registrazione';
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}

const addButton = (id, funString, text) => {
    const btn = document.createElement('button')
    btn.id = id
    btn.setAttribute('onclick', funString)
    btn.textContent = text
    controllerWrapper.append(btn)
}

const addMessage = (text) => {
    const msg = document.createElement('p')
    msg.textContent = text
    display.append(msg)
}

const addAudio = () => {
    const audio = document.createElement('audio')
    audio.controls = true
    audio.src = audioURL
    display.append(audio)
}

const application = (index) => {
    switch (State[index]) {
        case 'Initial':
            clearDisplay()
            clearControls()

            addButton('record', 'record()', 'Start Recording')
            break;

        case 'Record':
            clearDisplay()
            clearControls()

            addMessage('Recording...')
            addButton('stop', 'stopRecording()', 'Stop Recording')
            break

        case 'Download':
            clearControls()
            clearDisplay()

            addAudio()
            addButton('record', 'record()', 'Record Again')
            break

        default:
            clearControls()
            clearDisplay()

            addMessage('Your browser does not support mediaDevices')
            break;
    }

}

application(stateIndex)