<!DOCTYPE html>
<html lang="it">
<head>
    <link rel="icon" href="../images/siteIco.png" type="image/png">
    <%@page contentType="text/html;charset=UTF-8"%>

    <%
        Integer userId = (Integer) session.getAttribute("id");
        if(userId == null) {
        response.sendRedirect("../errorPage/403.html");
        }

    %>
    <meta charset="utf-8">
    <title>TalkAId - Politica sulla Privacy</title>
    <style>

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
            font-family: Georgia, sans-serif;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 10px;
        }
        label, input[type="time"] {
            display: block;
            margin-bottom: 10px;
        }

        #overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.7);
        }

        #popup {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            max-width: 80%; /* Imposta la larghezza massima del popup */
            max-height: 80%; /* Imposta l'altezza massima del popup */
            overflow: auto; /* Aggiunge una barra di scorrimento se il contenuto supera le dimensioni del popup */
        }

        #popup h2 {
            text-align: center;
        }

        #popup p {
            margin-bottom: 20px;
        }

        #popup button {
            padding: 10px 20px;
            background-color: #199a8e;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        .button {
            padding: 10px 20px;
            background-color: #199a8e;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <div id="overlay">
        <div id="popup">
            <h2>Condivisione Dati Scientifici</h2>
            <p>Desideriamo condividere i tuoi dati per scopi scientifici. Assicurati di leggere e accettare la nostra politica sulla privacy.</p>

            <h1>Politica sulla Privacy</h1>
            <p>Benvenuto nella nostra politica sulla privacy. La tua privacy è importante per noi e ci impegniamo a proteggere e rispettare le tue informazioni personali. Leggi attentamente questa pagina per comprendere come trattiamo i tuoi dati e come puoi gestire le tue preferenze.</p>

            <h2>Raccolta delle Informazioni</h2>
            <p>La tua interazione con il nostro sito web e i nostri servizi può comportare la raccolta di diverse informazioni, fondamentali per offrirti un'esperienza personalizzata e garantire il corretto funzionamento delle nostre piattaforme.<br><br>La trasparenza riguardo a questi processi è fondamentale, e di seguito ti forniamo un dettagliato approfondimento sulla raccolta di dati in queste situazioni.<br><br>

                1. Informazioni Raccolte<br>

                Quando interagisci con il nostro sito web o utilizzi i nostri servizi, raccogliamo una serie di informazioni che possono includere dati personali.<br><br>
                Questi dati possono variare in base al tipo di interazione e ai servizi utilizzati, ma potrebbero comprendere:<br><br>

                Nome e Cognome: Raccolti per personalizzare l'interazione e fornire una comunicazione più diretta e personalizzata.<br>

                Indirizzo Email: Utilizzato per scopi di comunicazione, conferma delle attività e fornitura di informazioni rilevanti.<br>

                Informazioni Identificative: Potremmo raccogliere altre informazioni identificative necessarie per specifici servizi o transazioni, garantendo la sicurezza e l'integrità delle operazioni.<br><br>

                2. Scopo della Raccolta<br>

                La raccolta di queste informazioni è finalizzata a migliorare la tua esperienza e ad assicurarci di fornirti i servizi richiesti in modo efficace. Utilizziamo questi dati per:<br><br>

                Personalizzare la tua esperienza utente, ad esempio, fornendo contenuti rilevanti e suggerimenti basati sulle tue preferenze.<br>

                Gestire e confermare le transazioni e le attività che svolgi attraverso il nostro sito o i nostri servizi.<br>

                Comunicare con te in merito a informazioni importanti, aggiornamenti, e offerte pertinenti.<br><br>

                3. Protezione dei Dati Personali<br>

                La sicurezza e la riservatezza dei tuoi dati personali sono di fondamentale importanza. Adottiamo misure di sicurezza avanzate per proteggere queste informazioni da accessi non autorizzati, modifiche o divulgazioni. I nostri sistemi utilizzano protocolli di crittografia, controlli di accesso e monitoraggio costante per garantire un ambiente sicuro.<br><br>

                4. Condivisione delle Informazioni<br>

                Le informazioni personali raccolte vengono trattate con la massima riservatezza e non sono condivise con terzi senza il tuo consenso, a meno che non sia necessario per fornirti i servizi richiesti o adempiere a obblighi legali.<br><br>

                Siamo impegnati a garantire una gestione responsabile dei tuoi dati personali e a rispettare la tua privacy durante ogni fase della tua interazione con noi. Per ulteriori dettagli o per esercitare i tuoi diritti relativi ai dati personali, ti invitiamo a consultare la sezione "Contatti" della nostra Politica sulla Privacy.</p>


            <h2>Uso delle Informazioni</h2>
            <p>La finalità primaria nella raccolta delle informazioni è migliorare la tua esperienza sul nostro sito web e garantire la fornitura efficiente dei servizi da te richiesti.<br><br>Ci impegniamo a utilizzare queste informazioni in modo responsabile e trasparente, rispettando le tue preferenze e la tua privacy.<br><br>Ecco come intendiamo impiegare i dati raccolti:<br><br>

                1. Miglioramento dell'Esperienza Utente<br>

                Utilizziamo le informazioni per personalizzare e ottimizzare la tua esperienza sul nostro sito. Questo comprende l'adattamento del contenuto, delle funzionalità e delle raccomandazioni in base alle tue preferenze.<br><br>Attraverso l'analisi dei dati, possiamo comprendere meglio le tue esigenze e offrirti un servizio più mirato.<br><br>

                2. Fornitura di Servizi Richiesti<br>

                Le informazioni raccolte sono utilizzate per eseguire le operazioni richieste e per fornirti i servizi che hai selezionato. Ciò può includere la gestione di account, la conferma di transazioni e la risoluzione di eventuali problemi operativi.<br><br>L'uso di questi dati è strettamente correlato alla tua interazione con i nostri servizi.<br><br>

                3. Comunicazioni Informative e Promozionali<br>

                Potremmo utilizzare i tuoi dati per inviarti comunicazioni informative e promozionali, ma solo previa acquisizione del tuo consenso. Queste comunicazioni possono riguardare aggiornamenti sui nostri servizi, offerte speciali, eventi o risorse pertinenti.<br><br>Rispettiamo la tua scelta e garantiamo che tu abbia il controllo completo sulle comunicazioni che desideri ricevere.<br><br>

                4. Consenso dell'Utente<br>

                Prima di inviarti comunicazioni promozionali, otterremo il tuo consenso esplicito. Puoi gestire le tue preferenze di consenso attraverso le impostazioni del tuo account o seguendo le istruzioni fornite nelle nostre comunicazioni.<br><br>Riconosciamo e rispettiamo il tuo diritto di decidere come desideri interagire con noi.<br><br>

                Il nostro impegno è garantire che l'uso delle tue informazioni migliori la tua esperienza senza compromettere la tua privacy. Continueremo a cercare modi innovativi e responsabili per implementare queste pratiche, mantenendo sempre il rispetto per la tua volontà e le tue preferenze.<br><br>Per ulteriori dettagli sulla gestione del consenso e sulla tua privacy, ti invitiamo a consultare la nostra Politica sulla Privacy.</p>


            <h2>Condivisione delle Informazioni</h2>
            <p>La tua privacy è al centro delle nostre preoccupazioni, e ci impegniamo a garantire la massima protezione delle tue informazioni personali.<br><br>In linea con questo impegno, vogliamo chiarire il nostro approccio alla condivisione delle tue informazioni con terzi:<br><br>

                1. Principio Fondamentale<br>

                Non vendiamo, noleggiamo né cediamo le tue informazioni personali a terzi senza il tuo consenso. Questo principio è essenziale per garantire che tu abbia il controllo completo sulla condivisione dei tuoi dati e che la tua privacy sia sempre rispettata.<br><br>

                2. Eccezioni Espressamente Descritte<br>

                La condivisione delle tue informazioni potrebbe avvenire solo nei casi espressamente descritti nella nostra Politica sulla Privacy. Queste situazioni specifiche sono attentamente definite e limitate per garantire che qualsiasi condivisione di dati avvenga nel rispetto delle tue aspettative e dei nostri impegni nei tuoi confronti.<br><br>

                3. Obblighi Legali<br>

                In circostanze eccezionali, potremmo essere obbligati a condividere le tue informazioni personali con terzi per rispettare leggi, normative o richieste legali in corso. Tale divulgazione sarà effettuata nel rispetto delle leggi sulla privacy e con l'obiettivo di proteggere i tuoi diritti e la nostra integrità.<br><br>

                4. Condivisione Consensuale<br>

                Se mai decidessimo di condividere le tue informazioni personali con terzi per finalità diverse da quelle indicate in questa Politica sulla Privacy, otterremo il tuo consenso esplicito prima di procedere. Rispettiamo la tua volontà e garantiamo che tu sia informato e coinvolto nelle decisioni relative alla tua privacy.<br><br>

                Il nostro impegno è preservare la riservatezza dei tuoi dati personali e mantenere una chiara trasparenza riguardo alle pratiche di condivisione delle informazioni. Per una comprensione più dettagliata di come trattiamo le tue informazioni personali, ti invitiamo a consultare la nostra Politica sulla Privacy.</p>


            <h2>Sicurezza delle Informazioni</h2>
            <p>La sicurezza delle tue informazioni personali è la nostra massima priorità.<br><br>Adottiamo rigorose misure di sicurezza per garantire che i tuoi dati siano al sicuro da qualsiasi minaccia potenziale.<br><br>Di seguito, sono descritte le pratiche e le tecnologie che implementiamo per proteggere le tue informazioni personali:<br><br>

                1. Misure di Sicurezza Comprehensive<br>

                Implementiamo un approccio olistico alla sicurezza, adottando misure preventive, detective e correttive per proteggere le tue informazioni da accessi non autorizzati, modifiche, divulgazioni o distruzioni non autorizzate.<br><br>Questo approccio comprensivo ci consente di identificare tempestivamente e rispondere a eventuali minacce o incidenti di sicurezza.<br><br>

                2. Crittografia delle Informazioni Sensibili<br>

                Tutte le informazioni sensibili che raccogliamo sono crittografate utilizzando protocolli di sicurezza avanzati.<br><br>La crittografia garantisce che anche in caso di accesso non autorizzato, i dati siano illeggibili senza le chiavi di decrittazione corrette.<br><br>Ciò aggiunge uno strato significativo di protezione alle tue informazioni personali.<br><br>

                3. Protocolli di Sicurezza Aggiornati<br>

                Ci impegniamo a utilizzare i protocolli di sicurezza più recenti e avanzati.<br><br>Questi protocolli vengono costantemente aggiornati per affrontare le nuove sfide e le minacce emergenti.<br><br>Ciò include l'implementazione di standard di crittografia robusti, la gestione sicura delle chiavi e l'adozione di procedure di sicurezza avanzate.<br><br>

                4. Monitoraggio Costante<br>

                I nostri sistemi sono monitorati costantemente per rilevare attività sospette o anomalie.<br><br>L'identificazione tempestiva di potenziali minacce consente di prendere misure immediate per proteggere le tue informazioni e mantenere un ambiente sicuro.<br><br>

                La tua fiducia è fondamentale per noi, e ci impegniamo a garantire che le tue informazioni personali siano gestite con la massima sicurezza.<br><br>Continueremo a investire nelle più recenti tecnologie e pratiche di sicurezza per proteggere la tua privacy in modo robusto.<br><br>Per ulteriori dettagli sulla nostra sicurezza delle informazioni, ti invitiamo a consultare la nostra Politica sulla Privacy.</p>


            <h2>Cookie e Tecnologie Simili</h2>
            <p>Per offrirti un'esperienza online personalizzata e ottimizzata, utilizziamo cookie e tecnologie simili.<br><br>Questi strumenti ci consentono di comprendere meglio come interagisci con il nostro sito web e i nostri servizi.<br><br>Di seguito, spieghiamo come impieghiamo i cookie e come puoi gestire le tue preferenze.<br><br>

                1. Utilizzo dei Cookie e Tecnologie Simili<br>

                I cookie sono piccoli file di testo che vengono memorizzati sul tuo dispositivo quando visiti il nostro sito web.<br><br>Utilizziamo anche tecnologie simili come web beacon, pixel e script per raccogliere e tracciare informazioni.<br><br>Queste tecnologie ci forniscono dati utili per migliorare la funzionalità del sito, comprendere il comportamento degli utenti e personalizzare il contenuto.<br><br>

                2. Miglioramento dell'Esperienza Utente<br>

                I cookie ci consentono di offrirti un'esperienza più personalizzata.<br><br>Possono memorizzare le tue preferenze, le pagine visitate e le interazioni precedenti, consentendoci di adattare il contenuto alle tue esigenze specifiche.<br><br>Questo ci aiuta a rendere il nostro sito più efficiente e pertinente per te.<br><br>

                3. Gestione delle Preferenze sui Cookie<br>

                Rispettiamo la tua privacy e ti offriamo il controllo sulle tue preferenze sui cookie.<br><br>Puoi gestire le impostazioni dei cookie attraverso le opzioni del tuo browser.<br><br>La maggior parte dei browser consente di accettare o rifiutare tutti i cookie, di ricevere un avviso quando viene inviato un cookie o di eliminare i cookie periodicamente.<br><br>Modificando queste impostazioni, potrai adattare l'utilizzo dei cookie alle tue preferenze personali.<br><br>

                4. Cookie di Terze Parti<br>

                Alcuni cookie potrebbero provenire da terze parti, come partner pubblicitari o servizi di analisi.<br><br>Questi cookie vengono utilizzati per fornire annunci mirati o per analizzare le prestazioni del sito.<br><br>Ti invitiamo a consultare la nostra Politica sulla Privacy per maggiori dettagli su come collaboriamo con terze parti e su come proteggiamo la tua privacy.<br><br>

                La tua esperienza online è importante per noi, e ci impegniamo a garantire che tu possa gestire le tue preferenze sui cookie in modo trasparente e conforme alle tue esigenze.<br><br>Per ulteriori dettagli sul nostro utilizzo dei cookie, ti invitiamo a consultare la nostra Informativa sui Cookie.</p>


            <h2>Modifiche a questa Politica sulla Privacy</h2>
            <p>Per garantire che la nostra Politica sulla Privacy sia allineata alle nostre pratiche più recenti e rispecchi i più alti standard di protezione dei dati, ci riserviamo il diritto di modificarla periodicamente.<br><br>Questi aggiornamenti potrebbero derivare da nuove leggi sulla privacy, avanzamenti tecnologici o modifiche nei nostri servizi.<br><br>Di seguito, spieghiamo il nostro approccio agli aggiornamenti e come puoi rimanere informato:<br><br>

                1. Ragioni degli Aggiornamenti<br>

                Gli aggiornamenti della Politica sulla Privacy possono essere necessari per riflettere le modifiche nella gestione dei dati personali, migliorare la trasparenza o rispondere a nuove esigenze normative.<br><br>Ogni modifica sarà finalizzata a rafforzare la tua fiducia nei nostri servizi e a garantire un trattamento sempre più efficace delle tue informazioni personali.<br><br>

                2. Revisione Periodica<br>

                Ti consigliamo di rivedere regolarmente questa pagina per essere sempre informato su come proteggiamo le tue informazioni.<br><br>Saranno fornite indicazioni chiare sulla data dell'ultima modifica in modo che tu possa identificare facilmente eventuali cambiamenti.<br><br>La tua consapevolezza è fondamentale per noi, e vogliamo garantire che tu sia sempre informato sui nostri impegni verso la tua privacy.<br><br>

                3. Notifiche sugli Aggiornamenti<br>

                Qualora apportassimo modifiche significative alla Politica sulla Privacy, potremmo fornire notifiche aggiuntive per assicurarci che tu sia pienamente consapevole di tali cambiamenti.<br><br>Queste notifiche potrebbero essere comunicate attraverso il nostro sito web, messaggi email o altri mezzi di comunicazione ritenuti appropriati.<br><br>

                La tua fiducia è di importanza cruciale per noi, e vogliamo assicurarci che tu abbia tutte le informazioni necessarie per comprendere come proteggiamo le tue informazioni personali.<br><br>Continueremo a impegnarci per garantire la massima trasparenza e aderenza a gli standard più elevati di privacy e sicurezza.</p>


            <h2>Contatti</h2>
            <p>Siamo qui per assisterti e rispondere a qualsiasi domanda o preoccupazione che tu possa avere sulla nostra Politica sulla Privacy o sui tuoi diritti relativi ai dati personali.<br><br>Per facilitare la comunicazione, ti invitiamo a contattarci all'indirizzo email dedicato:<br><br>

                talkAid@gov.it<br><br>

                Come Contattarci:<br>

                Invia un'email all'indirizzo sopra indicato specificando il motivo del tuo contatto.<br><br>Indica chiaramente se la tua richiesta riguarda la Politica sulla Privacy o se desideri esercitare i tuoi diritti relativi ai dati personali.<br><br>

                Risposta Rapida e Trasparente:<br>

                Ci impegniamo a rispondere alle tue domande o richieste il più rapidamente possibile.<br><br>Forniremo informazioni dettagliate e trasparenti in merito alla nostra Politica sulla Privacy e ai tuoi diritti in relazione ai dati personali.<br><br>

                Esercizio dei Diritti sui Dati Personali:<br>

                Se desideri esercitare i tuoi diritti relativi ai dati personali, quali l'accesso, la rettifica, la cancellazione o l'opposizione al trattamento, ti preghiamo di indicarlo chiaramente nella tua comunicazione.<br><br>Faremo del nostro meglio per soddisfare la tua richiesta in conformità con le leggi sulla privacy applicabili.<br><br>

                La tua privacy è importante per noi, e siamo qui per fornirti il supporto necessario e rispondere alle tue domande.<br><br>Apprezziamo la tua fiducia e la tua collaborazione nel garantire la corretta gestione delle tue informazioni personali.</p>


            <p>Grazie per la tua fiducia e per aver scelto di interagire con noi.</p>

            <div style="display: flex; justify-content: space-between;">
                <button onclick="accettaCondivisione()">Accetto</button>
                <button class="button" onclick="nonAccetto()">Non Accetto</button>
            </div>
        </div>
    </div>

    <form id="timeForm" action="../register" method="GET">
        <input type="hidden" name="type" value="emailTime">
        <div>
            <h3 style="text-align:center" >Seleziona una fascia oraria dove preferisci ricevere delle notifiche</h3>
        </div>
        <div>
            <label for="startTime">Dalle:</label>
            <input type="time" id="startTime" name="startTime" onchange="validateTimes()">
        </div>
        <div>
            <label for="endTime">Alle:</label>
            <input type="time" id="endTime" name="endTime" onchange="validateTimes()">
        </div>
        <button class="button" type="submit">Salva</button>
    </form>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha384-ZvpUoO/+PpLXR1lu4jmpXWu80pZlYUAfxl5NsBMWOEPSjUn/6Z/hRTt8+pR6L4N2" crossorigin="anonymous"></script>
    <script src="../JS/legal.js"></script>
</body>
</html>