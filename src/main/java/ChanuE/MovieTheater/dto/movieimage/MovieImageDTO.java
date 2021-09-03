package ChanuE.MovieTheater.dto.movieimage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieImageDTO {

    private String uuid;
    private String imageName;
    private String path;

    public String getImageURL() {
        try {
            // 인코딩. 코드화.. 컴퓨터가 알아들을 수 있는 숫자로.. 한글이 들어가면 못알아먹음..
            return URLEncoder.encode(path+ "/" + uuid + "_" + imageName, "UTF-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL() {
        try {
            return URLEncoder.encode(path+ "/s_" + uuid + "_" + imageName, "UTF-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }
}
