package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_DAN;
import seedu.address.model.roomservice.RoomServiceType;
import seedu.address.testutil.TypicalRoomService;

public class JsonAdaptedRoomServiceTest {

    @Test
    public void toModelType_validRoomServiceDining_returnsRoomService() throws Exception {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(TypicalRoomService.ROOM_SERVICE_DAN_DINING);
        assertEquals(TypicalRoomService.ROOM_SERVICE_DAN_DINING, roomService.toModelType());
    }

    @Test
    public void toModelType_validRoomServiceMassage_returnsRoomService() throws Exception {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(TypicalRoomService.ROOM_SERVICE_DAN_MASSAGE);
        assertEquals(TypicalRoomService.ROOM_SERVICE_DAN_MASSAGE, roomService.toModelType());
    }

    @Test
    public void toModelType_validRoomServiceWifi_returnsRoomService() throws Exception {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(TypicalRoomService.ROOM_SERVICE_DAN_WIFI);
        assertEquals(TypicalRoomService.ROOM_SERVICE_DAN_WIFI, roomService.toModelType());
    }

    @Test
    public void toModelType_validRoomServiceTypeDining_returnsRoomService() throws Exception {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(VALID_BOOKING_ID_DAN, RoomServiceType.DINING);
        assertEquals(TypicalRoomService.ROOM_SERVICE_DAN_DINING, roomService.toModelType());
    }

    @Test
    public void toModelType_validRoomServiceTypeMassage_returnsRoomService() throws Exception {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(VALID_BOOKING_ID_DAN, RoomServiceType.MASSAGE);
        assertEquals(TypicalRoomService.ROOM_SERVICE_DAN_MASSAGE, roomService.toModelType());
    }

    @Test
    public void toModelType_validRoomServiceTypeWifi_returnsRoomService() throws Exception {
        JsonAdaptedRoomService roomService = new JsonAdaptedRoomService(VALID_BOOKING_ID_DAN, RoomServiceType.WIFI);
        assertEquals(TypicalRoomService.ROOM_SERVICE_DAN_WIFI, roomService.toModelType());
    }


}

