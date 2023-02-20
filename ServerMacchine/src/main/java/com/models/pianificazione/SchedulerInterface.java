package com.models.pianificazione;

import java.util.List;

import lotto.Lavorazione;
import lotto.Lotto;
import macchina.Macchina;

public interface SchedulerInterface {
	/**
	 * algoritmo di scheduling dei lotti
	 * @param lotti
	 * @return
	 */
	public List<Lavorazione> getSchedule(List<Lotto> lotti, List<Macchina> macchine, int maxSlot);
	/**
	 * serve per ottenere il residuo di schedulazione
	 * @return
	 */
	public List<Lotto> getLottiResidui();
}