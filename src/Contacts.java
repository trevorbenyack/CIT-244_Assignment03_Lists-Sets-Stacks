import com.sun.codemodel.internal.JForEach;

import java.util.*;

public class Contacts {

    Collection<String> contactsCollection;

    // CONSTRUCTORS
    public Contacts(HashSet<String> hashSet) {

        contactsCollection = new HashSet<>();
    }

    // Set is not instantiatable, implementing LinkedHashSet and TreeSet instead
    public Contacts(LinkedHashSet<String> linkedHashSet) {
        contactsCollection = linkedHashSet;
    }
    public Contacts(TreeSet<String> treeSet) {
        contactsCollection = treeSet;
    }
    public Contacts(ArrayList<String> arrayList) {
        contactsCollection = arrayList;
    }

    // Queue is not instantiatable, LinkedList implements Queue
    public Contacts(LinkedList<String> linkedList) {
        contactsCollection = linkedList;
    }
    public Contacts(Stack<String> stack) {
        contactsCollection = stack;
    }


    // METHODS

    // Receives a name and phone number, stores it in an array, and adds the array to contactsCollection
    public Collection addContact(String name, String phoneNum) {

        String contactString = name + "__" + phoneNum;
        contactsCollection.add(contactString);

        return contactsCollection;
    }

    // Receives the name of a contact as a string, and then deletes that entry from the data structure
    // returns 1 if successful, 0 if the contact was not found
    public int removeContact(String name) {

        int success = 0;

        // used to temporarily hold data structure
        Collection<String> removedContactCollection = checkStackLinkedList();

        // to remove a contact entry, a regular expression is used to search for the name substring.
        // iterator is used b/c the .contains() method cannot be used for substrings.
        Iterator<String> iterator = removedContactCollection.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().matches(name + ".*")) {
                iterator.remove();
                success = 1;
            }
        }

        // if contactsCollection was originally a Stack or LinkedList, a new instance is created and the data is
        // copied back into it; otherwise, the removedContactCollection is assigned to contactsCollection
        if (contactsCollection instanceof Stack || contactsCollection instanceof LinkedList) {
            convertToStackLinkList(removedContactCollection);
        } else {
            contactsCollection = removedContactCollection;
        }

        return success;
    }

    public ArrayList<String> sortContactsByName () {
        ArrayList<String> sortedContactCollection = new ArrayList<>(contactsCollection);

        Collections.sort(sortedContactCollection);

        for (String string :
                sortedContactCollection) {
        }

        return sortedContactCollection;
    }

    public ArrayList<String> reverseOrder () {
        ArrayList<String> reversedContactCollection = new ArrayList<>(contactsCollection);

        Collections.reverse(reversedContactCollection);

        for (String string :
                reversedContactCollection) {
        }

        return reversedContactCollection;
    }

    public ArrayList<String> getPhoneNumber(String name) {

        // used to temporarily hold data structure
        Collection<String> tempContactCollection = checkStackLinkedList();
        ArrayList<String> resultsList = new ArrayList<>();

        for (String contactString : tempContactCollection) {
            if (contactString.toLowerCase().matches(name.toLowerCase() + ".*")) {
                resultsList.add(contactString);
            }
        }
        if (resultsList.isEmpty()) {
            resultsList.add("__No contacts found.");
        }

        return resultsList;
    }

    public void seedData() {
        this.addContact("Jim Johnson", "123-345-3422");
        this.addContact("Janine Jameson", "098-765-4321");
        this.addContact("Jerry Jolosky", "111-111-1111");
        this.addContact("John Jenkins", "222-222-2222");
        this.addContact("Jenna Joans", "333-333-3333");
    }


    /* ------------------------------------Helper methods------------------------------------------------ */

    // returns a copy of contactsCollection as an ArrayList if it is a Stack or LinkedList; otherwise returns the
    // original contactCollection
    private Collection<String> checkStackLinkedList () {
        // used to temporarily hold data structure
        Collection<String> tempCollection;

        // Converts Stack or LinkedList into an ArrayList b/c Queues cannot have their inner elements directly accessed.
        if (contactsCollection instanceof Stack || contactsCollection instanceof LinkedList) {

            // the ArrayList is assigned to tempContact Collection so general Collection methods can be performed on it.
            return new ArrayList<>(contactsCollection);

        } else {
            return contactsCollection;
        }
    }

    // converts the Collection<String> passed in to a Stack or LinkedList depending on the contactsCollection actual type
    private void convertToStackLinkList (Collection<String> tempList) {
        if (contactsCollection instanceof Stack) {
            contactsCollection = new Stack<String>();
        } else if (contactsCollection instanceof Queue){
            contactsCollection = new LinkedList<>();
        }
        tempList.stream().forEach(each -> contactsCollection.add((String)each));

    }
}
