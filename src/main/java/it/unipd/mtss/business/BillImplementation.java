////////////////////////////////////////////////////////////////////
// [Giulia] [Dentone] [2001687]
// [Andrea] [Stecca] [2016104]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.List;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillImplementation {
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
        double toReturn = 0.0;
        for (EItem iterable_element : itemsOrdered) {
            toReturn += iterable_element.getPrezzo();
        }
        return toReturn;
    }
}