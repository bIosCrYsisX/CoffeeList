package rynkbit.tk.coffeelist.admin.invoice;

import rynkbit.tk.coffeelist.db.entity.Invoice;
import rynkbit.tk.coffeelist.db.facade.InvoiceFacade;

/**
 * Created by michael on 11/19/16.
 */
public class InvoiceController {
    InvoiceActivity mActivity;
    private InvoiceListAdapter listAdapter;

    public InvoiceController(InvoiceActivity invoiceActivity) {
        mActivity = invoiceActivity;
        listAdapter = new InvoiceListAdapter(this);
        refreshAdapter();
    }

    private void refreshAdapter() {
        listAdapter.setInvoiceList(InvoiceFacade.getInvoices(mActivity));
    }

    public InvoiceListAdapter getListAdapter() {
        return listAdapter;
    }

    public void deleteInvoice(Invoice invoice) {
        InvoiceFacade.remove(mActivity, invoice);
        refreshAdapter();
    }
}
