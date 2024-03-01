import java.util.ArrayList;

public class Parent extends User implements Observer{
    ArrayList<Notification> notifications;
    public Parent(String firstName, String lastName) {
        super(firstName, lastName);
        notifications = new ArrayList<>();
    }

    @Override
    public void update(Notification notification) {
        notifications.add(notification);
    }


    @Override
    public String toString() {
        return "Parent{" +
                super.getFirstName() + " " + super.getLastName() +
                " notifications=" + notifications +
                '}';
    }
}
