asm BowlingClub

import StandardLibrary
import LTLlibrary
import CTLlibrary

signature:

enum domain DPista = {NESSUNA,PRIMA,SECONDA,TERZA,QUARTA}
enum domain DGiocatori = {ZERO,UNO,DUE,TRE,QUATTRO}
enum domain DGiorniSettimana = {LUN,MAR,MER,GIO,VEN,SAB,DOM}
enum domain DStatoClub = {APERTO,CHIUSO}
domain DGuadagno subsetof Integer

controlled statoPista: DPista -> DGiocatori
controlled guadagnoGiornaliero: DGuadagno 
controlled guadagnoSettimanale: DGiorniSettimana -> DGuadagno
controlled statoClub: DStatoClub
controlled giornoCorrente: DGiorniSettimana

monitored nuoviGiocatori: DGiocatori
monitored liberaPista: DPista
monitored chiudiClub: Boolean
monitored apriClub: Boolean
monitored qualePista: DPista

definitions:
	
	domain DGuadagno = {0:100}

rule r_svuotapiste =
	par
		statoPista(PRIMA) := ZERO
		statoPista(SECONDA) := ZERO
		statoPista(TERZA) := ZERO
		statoPista(QUARTA) := ZERO
	endpar
	
rule r_gestisciClub($act in DStatoClub) =
	par
		r_svuotapiste[]
		if $act = CHIUSO then
			par
		 		guadagnoSettimanale(giornoCorrente) := guadagnoGiornaliero
				guadagnoGiornaliero := 0
				statoClub := CHIUSO
			
				if giornoCorrente = LUN then giornoCorrente := MAR endif
				if giornoCorrente = MAR then giornoCorrente := MER endif
				if giornoCorrente = MER then giornoCorrente := GIO endif
				if giornoCorrente = GIO then giornoCorrente := VEN endif
				if giornoCorrente = VEN then giornoCorrente := SAB endif
				if giornoCorrente = SAB then giornoCorrente := DOM endif
				if giornoCorrente = DOM then giornoCorrente := LUN endif 
			endpar
		else
			statoClub := APERTO
		endif
	endpar

	CTLSPEC ag(guadagnoGiornaliero >= 0)
	
	CTLSPEC (forall $giorno in DGiorniSettimana with ag(guadagnoSettimanale($giorno) >= 0))
	
	CTLSPEC ef(giornoCorrente = GIO)
	
	CTLSPEC ag(ef(statoClub = CHIUSO))
	
	CTLSPEC ef(statoPista(TERZA) = DUE and statoPista(SECONDA) = QUATTRO)
	
	CTLSPEC ef(statoPista(PRIMA) = QUATTRO and guadagnoGiornaliero > 50)
	
	CTLSPEC ag(statoPista(PRIMA) = UNO and statoPista(PRIMA)= DUE)
	
main rule r_main = 
	par
		if liberaPista != NESSUNA and nuoviGiocatori = ZERO and not apriClub and not chiudiClub then
			if statoClub = APERTO then
			par
				if liberaPista = PRIMA then statoPista(PRIMA) := ZERO endif
				if liberaPista = SECONDA then statoPista(SECONDA) := ZERO endif
				if liberaPista = TERZA then statoPista(TERZA) := ZERO endif
				if liberaPista = QUARTA then statoPista(QUARTA) := ZERO endif
			endpar
			endif
		endif
		
		if nuoviGiocatori != ZERO and liberaPista = NESSUNA and not apriClub and not chiudiClub then
			if statoClub = APERTO then
			choose $pistaLibera in DPista with statoPista($pistaLibera) = ZERO and $pistaLibera != NESSUNA do
				par
					statoPista($pistaLibera) := nuoviGiocatori
					if nuoviGiocatori = UNO then guadagnoGiornaliero := guadagnoGiornaliero + 15 endif
					if nuoviGiocatori = DUE then guadagnoGiornaliero := guadagnoGiornaliero + 30 endif
					if nuoviGiocatori = TRE then guadagnoGiornaliero := guadagnoGiornaliero + 45 endif
					if nuoviGiocatori = QUATTRO then guadagnoGiornaliero := guadagnoGiornaliero + 60 endif
				endpar	
			endif
		endif
		
		if apriClub and not chiudiClub and liberaPista = NESSUNA  and nuoviGiocatori = ZERO then
			r_gestisciClub[APERTO]
		endif
		
		if chiudiClub and not apriClub and liberaPista = NESSUNA and nuoviGiocatori = ZERO then
			r_gestisciClub[CHIUSO]
		endif
		
	endpar
	
	
	
default init s0:
function statoPista($d in DPista) = ZERO
function guadagnoSettimanale($s in DGiorniSettimana) = 0
function guadagnoGiornaliero = 0
function statoClub = CHIUSO
function giornoCorrente = LUN