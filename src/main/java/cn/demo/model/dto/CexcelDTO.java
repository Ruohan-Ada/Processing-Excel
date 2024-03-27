package cn.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Min;
import lombok.Data;

/**
 * @author adazhang
 * @date 2024/03/09
 */
@Data
public class CexcelDTO {

    @Data
    public static class Query {
        @ApiModelProperty("当前页，默认为1")
        @Min(value = 1, message = "current最小为1")
        private Integer current = 1;

        @ApiModelProperty("页大小，默认为10")
        @Min(value = 1, message = "size最小为1")
        private Integer size = 10;

        private String B;
        private Float aaS;
        private Float bbS;
        private Float ccS;
        private Float aaA;
        private Float bbA;
        private Float ccA;
        private Float aaSS;
        private Float aaC;
        private Float ddS;
    }

    @Data
    public static class Add {
        private String B;
        private Float aaS;
        private Float bbS;
        private Float ccS;
        private Float aaA;
        private Float bbA;
        private Float ccA;
        private Float aaSS;
        private Float aaC;
        private Float ddS;
    }

    @Data
    public static class Update {
        private String B;
        private Float aaS;
        private Float bbS;
        private Float ccS;
        private Float aaA;
        private Float bbA;
        private Float ccA;
        private Float aaSS;
        private Float aaC;
        private Float ddS;
    }

    @Data
    public static class Delete {
        private String B;
    }
}

