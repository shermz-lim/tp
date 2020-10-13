package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.room.Room;
import seedu.address.model.room.UniqueRoomList;


public class RoomBook implements ReadOnlyRoomBook {

    private final UniqueRoomList rooms;

    {
        rooms = new UniqueRoomList();
    }

    public RoomBook() {}

    /**
     * Creates an RoomBook using the rooms in the {@code toBeCopied}
     */
    public RoomBook(ReadOnlyRoomBook tobeCopied) {
        this();
        resetData(tobeCopied);
    }

    /// list level operations
    public ObservableList<Integer> getAvailableRooms(ObservableList<Integer> unavailableRooms) {
        return rooms.getComplementRooms(unavailableRooms);
    }

    public void addRoom(Room r) {
        rooms.add(r);
    }

    public void setRooms(List<Room> rooms) {
        this.rooms.setRooms(rooms);
    }

    /**
     * Resets the existing data of this {@code RoomBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRoomBook newData) {
        requireNonNull(newData);

        setRooms(newData.getRoomList());
    }

    /// room level operations
    public boolean hasRoom(int roomId) {
        return rooms.contains(roomId);
    }

    public Room getRoom(int roomId) {
        return rooms.getRoom(roomId);
    }

    /// util methods
    @Override
    public ObservableList<Room> getRoomList() {
        return rooms.asUnmodifiableObservableList();
    }
}