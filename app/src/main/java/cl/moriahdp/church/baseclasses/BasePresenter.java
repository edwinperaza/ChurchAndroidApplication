package cl.moriahdp.church.baseclasses;

public class BasePresenter<T> {

    protected BaseModel baseModel;
    protected T baseView;

    public BasePresenter(BaseModel model, T view) {
        this.baseModel = model;
        this.baseView = view;
    }

    public T getView() {
        return baseView;
    }
}
