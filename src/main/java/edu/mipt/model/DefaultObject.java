package edu.mipt.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DefaultObject {
    @XmlElement
    private final long id;
    @XmlElement
    private final String name;

    public DefaultObject(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
