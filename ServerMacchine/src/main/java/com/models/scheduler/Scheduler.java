package com.models.scheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.models.data.Lavorazione;
import com.models.data.Lotto;
import com.models.data.PrioritaLotto;
import com.models.macchine.Macchina;

public class Scheduler implements SchedulerInterface {

	private List<Lotto> residui;

	private Scheduler scheduler = null;
	private int maxSlot;

	private Scheduler(int maxSlot) {
		this.maxSlot = maxSlot;
	}

	public Scheduler getScheduler(int maxSlot) {
		if (scheduler == null)
			scheduler = new Scheduler(maxSlot);
		return scheduler;
	}

	@Override
	public List<Lavorazione> getSchedule(List<Lotto> lotti, List<Macchina> macchine) {
		List<Lavorazione> lavorazioni = new ArrayList<>();
		boolean isOccupato[][] = new boolean[macchine.size()][maxSlot]; // default is false
		residui = new ArrayList<>();
		Collections.sort(lotti);
		
		for (Lotto lotto : lotti) {
			int countLavorazioni = 0;
			int countSlot = 0;
			while (!lotto.getListaLavorazioni().isEmpty() && countSlot < maxSlot) {
				for (int i = 0; i < macchine.size(); i++) {
					if (lotto.getListaLavorazioni().get(0).equals(macchine.get(i).getTipoMacchina())
							&& !isOccupato[i][countSlot]) {
						isOccupato[i][countSlot] = true;
						countLavorazioni++;
						lavorazioni.add(
								new Lavorazione(lotto.getIdLotto() + "-" + countLavorazioni, lotto, macchine.get(i).getidMacchina(), countSlot+1));
						lotto.getListaLavorazioni().remove(0);
					}
					countSlot++;
					if (!(countSlot < maxSlot)) {
						lotto.setPriorita(PrioritaLotto.alta);
						residui.add(lotto);
					}
				}
			}
		}
		return lavorazioni;
	}

	@Override
	public List<Lotto> getLottiResidui() {
		return residui;
	}

}
