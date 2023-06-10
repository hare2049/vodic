package com.example.vodic.database

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vodic.database.dao.*
import com.example.vodic.database.entity.*
import com.example.vodic.database.state.BodoviState
import com.example.vodic.database.state.FakultetState
import com.example.vodic.database.state.SmjerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VodicViewModel(
    private val fakultetDao: FakultetDao,
    private val smjerDao: SmjerDao,
    private val bodoviDao: BodoviDao,
    private val pitanjaDao: PitanjaDao,
    private val odgovoriDao: OdgovoriDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(FakultetState())
    val uiState: StateFlow<FakultetState> = _uiState.asStateFlow()

    val _stateFakultet = MutableStateFlow(FakultetState())
    val _stateSmjer = MutableStateFlow(SmjerState())
    val _stateBodovi = MutableStateFlow(BodoviState())

    private val _state = MutableStateFlow(FakultetState())

    fun start() {
        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                val pitanje = pitanjaDao.dajPitanje(1)
                val odgovori = odgovoriDao.dajOdgovore(1)
                withContext(Dispatchers.Main) {
                    _uiState.value =
                        _uiState.value.copy(trenutnoPitanje = pitanje, trenutniOdgovori = odgovori)
                }
            }
        }
    }

    fun insertData() {
        viewModelScope.launch {

            withContext(Dispatchers.IO) {

                /*

                fakultetDao.insertFakultet(Fakultet(naziv = "PRIRODNO-MATEMAIČKI FAKULTET"))
                fakultetDao.insertFakultet(Fakultet(naziv = "ELEKTROTEHNIČKI FAKULTET"))
                smjerDao.insertSmjer(Smjer(naziv = "Informacione tehnologije", odsjek = "Odsjek za matematičke i kompjuterske nauke", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Kompjuterske nauke", odsjek = "Odsjek za matematičke i kompjuterske nauke", fakultetId = 1))
                pitanjaDao.insertPitanje(Pitanja(text = "Da li vas više privlači teoretsko ili praktično znanje?"))
                pitanjaDao.insertPitanje(Pitanja(text = "Koji faktor vam je najvažniji pri odabiru karijere?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "Teoretsko znanje", pitanjeId = 1))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "Praktično znanje", pitanjeId = 1))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "visoka zarada", pitanjeId = 2))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "radna fleksibilnost", pitanjeId = 2))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "zadovoljstvo poslom", pitanjeId = 2))




                 */




                val faculties = pitanjaDao.getAllPitanja()
                withContext(Dispatchers.Main) {
                    _uiState.value = _uiState.value.copy(pitanja = faculties)
                }
            }
        }


    }

    fun ubaci(){
        /*
        val faculties = listOf(
            "PRIRODNO-MATEMATICKI FAKULTET",
            "MEDICINSKI FAKULTET",
            "STOMATOLOSKI FAKULTET",
            "PEDAGOSKI FAKULTET",
            "POLJOPRIVREDNO-PREHRAMBENI FAKULTET",
            "MASINSKI FAKULTET",
            "AKADEMIJA LIKOVNIH UMJETNOSTI",
            "SUMARSKI FAKULTET",
            "VETERINARSKI FAKULTET",
            "FAKULTET ZDRAVSTVENIH STUDIJA",
            "AKADEMIJA SCENSKIH UMJETNOSTI",
            "ARHITEKTONSKI FAKULTET",
            "ELEKTROTEHNICKI FAKULTET",
            "FAKULTET ISLAMSKIH NAUKA",
            "FAKULTET KRIMINALISTICKIH NAUKA",
            "FAKULTET POLITICKIH NAUKA",
            "FAKULTET SPORTA I TJELESNOG ODGOJA",
            "FAKULTET ZA SAOBRACAJ I KOMUNIKACIJE",
            "FARMACEUTSKI FAKULTET",
            "FILOZOFSKI FAKULTET",
            "GRAĐEVINSKI FAKULTET"
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                faculties.forEach { faculty ->
                    fakultetDao.insertFakultet(Fakultet(naziv = faculty))
                }
                pitanjaDao.insertPitanje(Pitanja(text = "Da li vas vise privlaci teoretsko ili prakticno znanje?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "teoretsko znanje", pitanjeId = 1))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "prakticno znanje", pitanjeId = 1))
                pitanjaDao.insertPitanje(Pitanja(text = "Da li ste zainteresovani za istrazivacke radove i inovacije?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "Da", pitanjeId = 2))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "Ne", pitanjeId = 2))
                pitanjaDao.insertPitanje(Pitanja(text = "Da li vam je vazno raditi samostalno ili u timu?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "samostalno rad", pitanjeId = 3))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "rad u timu", pitanjeId = 3))
                pitanjaDao.insertPitanje(Pitanja(text = "Uzivate li u prakticnim eksperimentima i laboratorijskim radovima?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "Da", pitanjeId = 4))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "Ne", pitanjeId = 4))
                pitanjaDao.insertPitanje(Pitanja(text = "Da li vas vise privlaci kreativno ili analiticko razmisljanje?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "Kreativno razmisljanje", pitanjeId = 5))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "Analiticko razmisljanje", pitanjeId = 5))
                pitanjaDao.insertPitanje(Pitanja(text = "Koje vrsta okruzenja za ucenje vam vise odgovara?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "Strukturirano okruzenje", pitanjeId = 6))
                odgovoriDao.insertOdgovor(Odgovori(odgovor = "Fleksibilno okruzenje", pitanjeId = 6))
                smjerDao.insertSmjer(Smjer(naziv = "Nastavnički smjer", odsjek = "Odsjek za biologiju", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Smjer biohemije i fiziologije", odsjek = "Odsjek za biologiju", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Smjer ekologija", odsjek = "Odsjek za biologiju", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Smjer genetika", odsjek = "Odsjek za biologiju", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Smjer mikrobiologija", odsjek = "Odsjek za biologiju", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Nastavnički smjer", odsjek = "Odsjek za fiziku", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Opći smjer", odsjek = "Odsjek za fiziku", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Nastavnički smjer", odsjek = "Odsjek za geografiju", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Regionalno i prostorno planiranje", odsjek = "Odsjek za geografiju", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Turizam i zaštita životne sredine", odsjek = "Odsjek za geografiju", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Nastavnički smjer", odsjek = "Odsjek za hemiju", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Opšti smjer", odsjek = "Odsjek za hemiju", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Kontrola kvaliteta i zaštita okoliša", odsjek = "Odsjek za hemiju", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Nastavnički smjer (Matematika)", odsjek = "Odsjek za matematiku", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Nastavnički smjer (Matematika i Informatika)", odsjek = "Odsjek za matematiku", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Opći smjer", odsjek = "Odsjek za matematiku", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Primjenjena matematika", odsjek = "Odsjek za matematiku", fakultetId = 1))
                smjerDao.insertSmjer(Smjer(naziv = "Teorijska kompjuterska nauka", odsjek = "Odsjek za matematiku", fakultetId = 1))
                pitanjaDao.insertPitanje(Pitanja(text="Da li preferirate putovanja i rad s klijentima ili rad u uredu?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="putovanja i rad sa klijentima", pitanjeId=7))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="rad u uredu", pitanjeId=7))
                pitanjaDao.insertPitanje(Pitanja(text="Koji tip poslovnog okruženja vam više odgovara?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="dinamično i promjenjivo", pitanjeId=8))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="stabilno i predvidljivo", pitanjeId=8))
                pitanjaDao.insertPitanje(Pitanja(text="Da li biste voljeli raditi s novim tehnologijama ili tradicionalnim metodama?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="nove tehnologije", pitanjeId=9))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="tradicionalne metode", pitanjeId=9))
                pitanjaDao.insertPitanje(Pitanja(text="Da li biste radije radili u javnom sektoru ili privatnom sektoru?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="javni sektor", pitanjeId=10))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="privatni sektor", pitanjeId=10))
                pitanjaDao.insertPitanje(Pitanja(text="Da li biste više voljeli raditi s ljudima ili s podacima?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="rad sa ljudima", pitanjeId=11))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="rad sa podacima", pitanjeId=11))
                pitanjaDao.insertPitanje(Pitanja(text="Koji su vaši jaki osobni vještine?"))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="Kreativnost i inovativnost", pitanjeId=12))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="Analitičke sposobnosti i logika", pitanjeId=12))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="Tehničke vještine", pitanjeId=12))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="Komunikacija i suradnja", pitanjeId=12))
                odgovoriDao.insertOdgovor(Odgovori(odgovor="Organizacijske sposobnosti i liderstvo", pitanjeId=12))
            }}

         */
    }

    fun dajPitanje(broj: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val pitanje = pitanjaDao.dajPitanje(broj)
                withContext(Dispatchers.Main) {
                    _uiState.value = _uiState.value.copy(trenutnoPitanje = pitanje)
                }
            }
        }
    }

    fun dajOdgovore(broj: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val odgovori = odgovoriDao.dajOdgovore(broj)
                withContext(Dispatchers.Main) {
                    _uiState.value = _uiState.value.copy(trenutniOdgovori = odgovori)
                }
            }
        }
    }

    fun dajPitanja(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val pitanja = pitanjaDao.getAllPitanja()
                withContext(Dispatchers.Main) {
                    _uiState.value = _uiState.value.copy(pitanja = pitanja)
                }

            }
        }
    }

    fun update(odgovor: String, pitanje: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                val pitanjeId = withContext(Dispatchers.IO) {
                    pitanjaDao.dajIdPitanja(pitanje)
                }
                val updatedListaOdgovora = _uiState.value.listaOdgovora.toMutableList()
                val index = pitanjeId - 1
                updatedListaOdgovora[index] = odgovor

                val iducePitanje = pitanjaDao.dajPitanje(pitanjeId + 1)
                val iduciOdgovori = odgovoriDao.dajOdgovore(pitanjeId + 1)

                withContext(Dispatchers.IO) {
                    _uiState.value = _uiState.value.copy(
                        listaOdgovora = updatedListaOdgovora,
                        trenutnoPitanje = iducePitanje,
                        trenutniOdgovori = iduciOdgovori
                    )
                }
            }
        }
    }

    fun update2(odgovor: String, pitanje: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                val pitanjeId = withContext(Dispatchers.IO) {
                    pitanjaDao.dajIdPitanja(pitanje)
                }
                val updatedListaOdgovora = _uiState.value.listaOdgovora.toMutableList()
                val index = pitanjeId - 1
                updatedListaOdgovora[index] = odgovor

                val iducePitanje = pitanjaDao.dajPitanje(pitanjeId - 1)
                val iduciOdgovori = odgovoriDao.dajOdgovore(pitanjeId - 1)

                withContext(Dispatchers.IO) {
                    _uiState.value = _uiState.value.copy(
                        listaOdgovora = updatedListaOdgovora,
                        trenutnoPitanje = iducePitanje,
                        trenutniOdgovori = iduciOdgovori
                    )
                }
            }
        }
    }

    fun setPutanja(varij: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _uiState.value = _uiState.value.copy(putanja = varij)
            }
        }
    }

    fun onSmjerEvent(eventSmjer: SmjerEvent) {
        when(eventSmjer) {
            SmjerEvent.SaveSmjer -> TODO()
            is SmjerEvent.setFakultetId -> TODO()
            is SmjerEvent.setNaziv -> TODO()
            is SmjerEvent.setOdsjek -> TODO()
        }
    }

    fun onBodoviEvent(eventBodovi: BodoviEvent) {
        when(eventBodovi) {
            BodoviEvent.SaveBodovi -> TODO()
            is BodoviEvent.setBodovi -> TODO()
            is BodoviEvent.setBrojPitanja -> TODO()
            is BodoviEvent.setOdgovor -> TODO()
            is BodoviEvent.setSmjerId -> TODO()
        }
    }


    /*
    fun funkcija1(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val smjerovi = smjerDao.getAllSmjerovi()
                val array = mutableStateListOf<Pair<Int, Int>>()

                for(smjer in smjerovi){

                    val bodovi = funkcija2(smjer.id)
                    val pair = Pair(smjer.id, bodovi)
                    array.add(pair)
                }
                val sortedArray = array.sortedByDescending { it.second }
                val topThreeElements = sortedArray.take(3)
                val dividedArray = topThreeElements.map {
                    val dividedBodovi = it.second // zamijeniti sa brojem pitanja
                    it.first to dividedBodovi
                }
                withContext(Dispatchers.Main) {
                    _uiState.value = _uiState.value.copy(ponudjeniSmjerovi = dividedArray)
                }
            }
        }
    }


    fun funkcija2(smjerId: Int): Int{
        var bodovi = 0
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                for(i in 1..2){
                    val bb = bodoviDao.dajBodove(smjerId, i, _uiState.value.listaOdgovora[i-1])
                    bodovi += bb
                }
            }}
        return bodovi
    }

     */
}