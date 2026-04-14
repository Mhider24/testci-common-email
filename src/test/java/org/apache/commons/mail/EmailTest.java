//Mohammad Hider Ahmad - Assignment 3
package org.apache.commons.mail;

import static org.junit.Assert.*;

import java.util.Date;

import javax.mail.Session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {

    private EmailConcrete email;

    @Before
    public void setUp() throws Exception {
        email = new EmailConcrete();

        email.setHostName("smtp.test.com");
        email.setFrom("from@test.com");
    }

    @After
    public void tearDown() {
        email = null;
    }

    //addBcc

    @Test
    public void testAddBccValidEmails() throws Exception {
        email.addBcc("a@test.com");
        email.addBcc("b@test.com");

        assertEquals(2, email.getBccAddresses().size());
    }

    @Test(expected = EmailException.class)
    public void testAddBccInvalidEmail() throws Exception {
        email.addBcc("invalid-email");
    }

    @Test(expected = EmailException.class)
    public void testAddBccBlankEmail() throws Exception {
        email.addBcc("");
    }

    //addCc

    @Test
    public void testAddCcValidEmail() throws Exception {
        email.addCc("cc@test.com");

        assertEquals(1, email.getCcAddresses().size());
    }

    @Test(expected = EmailException.class)
    public void testAddCcInvalidEmail() throws Exception {
        email.addCc("wrong");
    }

    //addHeader

    @Test
    public void testAddHeader() {
        email.addHeader("X-Test", "123");

        assertTrue(true);
    }

    //addReplyTo

    @Test
    public void testAddReplyTo() throws Exception {
        email.addReplyTo("reply@test.com", "Reply User");

        assertEquals(1, email.getReplyToAddresses().size());
    }

    //setFrom

    @Test
    public void testSetFromValid() throws Exception {
        email.setFrom("new@test.com");

        assertNotNull(email.getFromAddress());
        assertEquals("new@test.com", email.getFromAddress().getAddress());
    }

    @Test(expected = EmailException.class)
    public void testSetFromInvalid() throws Exception {
        email.setFrom("bad");
    }

    //getHostName

    @Test
    public void testGetHostName() {
        email.setHostName("smtp.example.com");

        assertEquals("smtp.example.com", email.getHostName());
    }

    //getSocketConnectionTimeout

    @Test
    public void testGetSocketConnectionTimeout() {
        email.setSocketConnectionTimeout(5000);

        assertEquals(5000, email.getSocketConnectionTimeout());
    }

    //getSentDate

    @Test
    public void testGetSentDateNotNull() {
        Date date = email.getSentDate();

        assertNotNull(date);
    }

    //getMailSession

    @Test
    public void testGetMailSessionNotNull() throws Exception {
        Session session = email.getMailSession();

        assertNotNull(session);
    }

    //buildMimeMessage

    @Test
    public void testBuildMimeMessageValid() throws Exception {
        email.addTo("to@test.com");
        email.setSubject("Test Subject");
        email.setMsg("Hello");

        email.buildMimeMessage();

        assertNotNull(email.getMimeMessage());
    }

    @Test(expected = EmailException.class)
    public void testBuildMimeMessageMissingTo() throws Exception {
        email.setSubject("Test");
        email.setMsg("Hello");

        email.buildMimeMessage();
    }
}