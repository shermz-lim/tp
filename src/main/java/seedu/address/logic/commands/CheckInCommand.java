package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_CONFLICTING_BOOKING;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_BOOKING;
import static seedu.address.commons.core.Messages.MESSAGE_EXCEED_DURATION;
import static seedu.address.commons.core.Messages.MESSAGE_PERSON_ID_MISSING;
import static seedu.address.commons.core.Messages.MESSAGE_ROOM_ID_MISSING;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.time.LocalDate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.exception.ConflictingBookingException;
import seedu.address.model.booking.exception.DuplicateBookingException;
import seedu.address.model.booking.exception.ExceedDurationStayException;

/**
 * Encapsulates the Check In feature.
 */
public class CheckInCommand extends Command {

    public static final String COMMAND_WORD = "checkIn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks in a person into the hotel. "
            + "Dates should be in the format yyyy-MM-dd. \n"
            + "Parameters: "
            + PREFIX_PERSON_ID + "PERSON_ID (must be an existing person Id) "
            + PREFIX_ROOM_ID + "ROOM_ID (must be an existing room Id) "
            + PREFIX_START_DATE + "START_DATE "
            + PREFIX_END_DATE + "END_DATE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PERSON_ID + "69 "
            + PREFIX_ROOM_ID + "2126 "
            + PREFIX_START_DATE + "2020-09-14 "
            + PREFIX_END_DATE + "2020-09-17";

    public static final String MESSAGE_ARGUMENTS = "Person id: %1$d, Room Id: %2$d, Start date: %3$s, End date: %4$s";
    public static final String MESSAGE_SUCCESS = "Successfully checked in: %s";

    private final int personId;
    private final int roomId;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Creates a CheckInCommand.
     *
     * @param personId the personId of the person checking in
     * @param roomId the roomId of the room that the person is checking into
     * @param startDate the start date of the booking
     * @param endDate the end date of the booking
     */
    public CheckInCommand(int personId, int roomId, LocalDate startDate, LocalDate endDate) {
        requireAllNonNull(personId, roomId, startDate, endDate);

        this.personId = personId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Booking booking;
        assert roomId > 0;
        assert startDate.isBefore(endDate);
        logger.info(String.format("Checking in person with id %s into room %s", personId, roomId));

        if (!model.hasPersonWithId(personId)) {
            throw new CommandException(MESSAGE_PERSON_ID_MISSING);
        }

        assert model.hasPersonWithId(personId);

        if (!model.hasRoom(roomId)) {
            throw new CommandException(MESSAGE_ROOM_ID_MISSING);
        }

        assert model.hasRoom(roomId);

        booking = new Booking(roomId, personId, startDate, endDate, true);
        int bookingId = booking.getId();

        try {
            model.addBooking(booking);
            return new CommandResult(String.format(MESSAGE_SUCCESS, booking));
        } catch (ConflictingBookingException e) {
            Booking.setNextAvailableId(bookingId);
            throw new CommandException(MESSAGE_CONFLICTING_BOOKING);
        } catch (DuplicateBookingException de) {
            Booking.setNextAvailableId(bookingId);
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        } catch (ExceedDurationStayException cde) {
            Booking.setNextAvailableId(bookingId);
            throw new CommandException(MESSAGE_EXCEED_DURATION);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CheckInCommand)) {
            return false;
        }

        // state check
        CheckInCommand e = (CheckInCommand) other;
        return personId == e.personId
                && roomId == e.roomId
                && startDate.isEqual(e.startDate)
                && endDate.isEqual(e.endDate);
    }
}
