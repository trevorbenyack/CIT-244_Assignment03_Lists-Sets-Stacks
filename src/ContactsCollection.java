import com.sun.codemodel.internal.JForEach;

import java.util.*;

public class ContactsCollection {

    Collection<String> contactsCollection;


    public ContactsCollection(HashSet<String> hashSet) {
        contactsCollection = hashSet;
    }

    // Set is not instantiatable, implementing LinkedHashSet and TreeSet instead
    public ContactsCollection(LinkedHashSet<String> linkedHashSet) {
        contactsCollection = linkedHashSet;
    }
    public ContactsCollection(TreeSet<String> treeSet) {
        contactsCollection = treeSet;
    }
    public ContactsCollection(ArrayList<String> arrayList) {
        contactsCollection = arrayList;
    }

    // Queue is not instantiatable, LinkedList implements Queue
    public ContactsCollection(LinkedList<String> linkedList) {
        contactsCollection = linkedList;
    }
    public ContactsCollection(Stack<String> stack) {
        contactsCollection = stack;
    }

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

    public Collection<String> sortContactsByName () {
        ArrayList<String> sortedContactCollection = new ArrayList<>(contactsCollection);

        Collections.sort(sortedContactCollection);

        for (String string :
                sortedContactCollection) {
            System.out.println(string);
        }

        return sortedContactCollection;
    }

    public Collection<String> reverseOrder () {
        ArrayList<String> reversedContactCollection = new ArrayList<>(contactsCollection);

        Collections.reverse(reversedContactCollection);

        for (String string :
                reversedContactCollection) {
            System.out.println(string);
        }

        return reversedContactCollection;
    }

    public String getPhoneNumber(String name) {

        // used to temporarily hold data structure
        Collection<String> tempContactCollection = checkStackLinkedList();

        for (String contactString : tempContactCollection) {
            if (contactString.matches(name + ".*")) {
                String[] contactSplit = contactString.split("__");
                return contactSplit[1];
            }
        }

        // if contact match was found, method exits returning the contact. Otherwise the method returns "-1"
        return "-1";

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

    private void convertToStackLinkList (Collection<String> tempList) {
        if (contactsCollection instanceof Stack) {
            contactsCollection = new Stack<String>();
        } else if (contactsCollection instanceof Queue){
            contactsCollection = new LinkedList<>();
        }
        tempList.stream().forEach(each -> contactsCollection.add((String)each));

    }
}
