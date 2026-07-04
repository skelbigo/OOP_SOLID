package org.example.factory;

import org.example.models.Room;
import org.example.models.rooms.FamilyRoom;
import org.example.models.rooms.LuxRoom;
import org.example.models.rooms.StandardRoom;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class RoomFactory {
    private final Map<RoomType, Supplier<Room>> registry;

    public RoomFactory() {
        Map<RoomType, Supplier<Room>> temp = new EnumMap<>(RoomType.class);
        temp.put(RoomType.STANDARD, StandardRoom::new);
        temp.put(RoomType.FAMILY, FamilyRoom::new);
        temp.put(RoomType.LUX, LuxRoom::new);
        registry = Collections.unmodifiableMap(temp);
    }

    public Room createRoom(RoomType type) {
        if (type == null) throw new IllegalArgumentException("Room type cannot be null");

        Supplier<Room> supplier = registry.get(type);
        if (supplier == null) throw new IllegalArgumentException("Unsupported room type: " + type);

        return supplier.get();
    }
}
