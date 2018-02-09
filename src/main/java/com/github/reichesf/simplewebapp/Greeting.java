package com.github.reichesf.simplewebapp;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Calendar;
import java.util.TimeZone;

// Identify the class for JAXB (rendering as XML) requires
// at a minimum the @XmlRootElement annotation. The name
// attribute is optional as the name of the class (lower case)
// is the default. Additional XML annotations
// may be supplied at the method and class level.
//
// For JAXB (XML) the default (no-arg) constructor is
// required.
//
// @XmlType with propOrder of 1 element is trivial but is
// included to show it's use.

@XmlType(propOrder = {"message"})

@XmlRootElement(name = "Greeting")
public final class Greeting
{
    public Greeting()
    {
        this.dateTime = this.getDateTimeAsString(System.currentTimeMillis());
    }

    public Greeting(final long nId, final String sMessage)
    {
        this.id = nId;
        this.message = sMessage;
    }

    @XmlAttribute(name="id")
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @XmlElement(name="message")
    public String getMessage()
    {
        return message;
    }

    public void setMessage(String content)
    {
        this.message = content;
    }

    @XmlAttribute(name="dateTime")
    public String getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(String dateTime)
    {
        this.dateTime = dateTime;
    }

    public void setDateTimeValue(final long nMillis)
    {
        this.dateTime = this.getDateTimeAsString(nMillis);
    }

    private static String getDateTimeAsString(final long nMillis)
    {
        String sRet = null;

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        cal.setTimeInMillis(nMillis);

        sRet = DatatypeConverter.printDateTime(cal);

        return sRet;
    }

    private String dateTime = null;
    private long id = 0;
    private String message = null;
}
