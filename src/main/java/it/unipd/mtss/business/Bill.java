////////////////////////////////////////////////////////////////////
// [Giulia] [Dentone] [2001687]
// [Andrea] [Stecca] [2016104]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.List;

import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import it.unipd.mtss.business.exception.BillException;

public interface Bill {
    double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException;

}