package Reservista.example.Backend.DTOs.SearchAndFilter;
import lombok.Data;

import java.util.List;

@Data
public class RoomSearchResultDTO {
    List<RoomDTO> roomDTOList;
}
