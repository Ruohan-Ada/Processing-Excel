package cn.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Min;
import lombok.Data;

/**
 * @author adazhang
 * @date 2024/03/09
 */
@Data
public class AexcelDTO {

    @Data
    public static class Query {
        @ApiModelProperty("当前页，默认为1")
        @Min(value = 1, message = "current最小为1")
        private Integer current = 1;

        @ApiModelProperty("页大小，默认为10")
        @Min(value = 1, message = "size最小为1")
        private Integer size = 10;

        private String A;
        private String B;
        private String C;
        private String D;
        private String E;
        private String F;
        private Integer aa;
        private Integer bb;
        private Integer cc;
        private Integer dd;
        private Integer ee;
    }

    @Data
    public static class Add {
        private String A;
        private String B;
        private String C;
        private String D;
        private String E;
        private String F;
        private Integer aa;
        private Integer bb;
        private Integer cc;
        private Integer dd;
        private Integer ee;
    }

    @Data
    public static class Update {
        private String A;
        private String B;
        private String C;
        private String D;
        private String E;
        private String F;
        private Integer aa;
        private Integer bb;
        private Integer cc;
        private Integer dd;
        private Integer ee;
    }

    @Data
    public static class Delete {
        private String A;
    }
}

