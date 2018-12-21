package guru.springframework.domain;
import io.swagger.annotations.ApiModelProperty;

public class Result {

    public Result(String description) {
        super();
        this.description = description;
    }

    @ApiModelProperty(notes = "The result description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}