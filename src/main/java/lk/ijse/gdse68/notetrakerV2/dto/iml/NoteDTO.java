package lk.ijse.gdse68.notetrakerV2.dto.iml;

import lk.ijse.gdse68.notetrakerV2.customObj.NoteResponse;
import lk.ijse.gdse68.notetrakerV2.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteDTO  implements SuperDTO, NoteResponse {
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private Long createDate;
    private String userId;


    public NoteDTO(String noteId, String restServices, String explainTheRest, String p1, String number) {
    }
}
