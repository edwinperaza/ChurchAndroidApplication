package cl.moriahdp.church.contact.presenter;

import cl.moriahdp.church.baseclasses.BasePresenter;
import cl.moriahdp.church.contact.model.ContactModel;
import cl.moriahdp.church.contact.view.ContactView;

public class ContactPresenter extends BasePresenter<ContactView> {

    private ContactModel contactModel;
    private ContactView contactView;

    public ContactPresenter(ContactModel model, ContactView view) {
        super(model, view);
        contactModel = model;
        contactView = view;
    }

}
