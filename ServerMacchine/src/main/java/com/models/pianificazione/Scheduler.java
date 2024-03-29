package com.models.pianificazione;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.models.lotto.Lavorazione;
import com.models.lotto.Lotto;
import com.models.lotto.PrioritaLotto;
import com.models.macchine.Macchina;
import com.utility.GestoreNotifiche;

public class Scheduler implements SchedulerInterface {

	private static List<Lotto> residui = null;

	private static Scheduler scheduler = null;
	GestoreNotifiche g;

	private Scheduler() {
		g = GestoreNotifiche.getGestoreNotifiche();
	}

	public static Scheduler getScheduler() {
		if (scheduler == null)
			scheduler = new Scheduler();
		return scheduler;
	}

	@Override
	public List<Lavorazione> getSchedule(List<Lotto> lotti, List<Macchina> macchine, int maxSlot) {
		List<Lavorazione> lavorazioni = new ArrayList<>();
		boolean isOccupato[][] = new boolean[macchine.size()][maxSlot]; // default is false
		residui = new ArrayList<>();
		Collections.sort(lotti);
		System.out.println(lotti.toString());
		
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
								new Lavorazione(lotto.getIdLotto() + "-" + countLavorazioni, lotto.getIdLotto(), lotto.getListaLavorazioni().get(0), macchine.get(i).getidMacchina(), countSlot+1));
						lotto.getListaLavorazioni().remove(0);
						break;
					}
				}
				countSlot++;
				if (!(countSlot < maxSlot)) {
					lotto.setPriorita(PrioritaLotto.alta);
					residui.add(lotto);
				}
			}
		}
		g.sendManager("Fine Pianificazione", "Fine Pianificazione");
		return lavorazioni;
	}

	@Override
	public List<Lotto> getLottiResidui() {
		return residui;
	}

}
