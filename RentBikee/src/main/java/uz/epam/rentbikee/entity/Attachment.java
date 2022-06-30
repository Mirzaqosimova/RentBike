package uz.epam.rentbikee.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attachment extends AbstractEntity {

    private String fileOriginalName;

    private long size;

    private String contentType;

    private String name;



  }
