import React from 'react';
import {Container, Row, Col, Form, Button, Alert} from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import './MailingListRegistrationPage.css';
import { useState } from 'react';
import axios from 'axios';

const MailingListRegistrationPage = () => {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");

    const handleClick = () => {
        if (firstName.length === 0 || 
            lastName.length === 0 ||
            email.length === 0) {

            return "Please fill all the inputs";
        } else {
            var message = "";
            setFirstName(firstName.trim());
            setLastName(lastName.trim());
            setEmail(email.trim());
            
            const userDetails = {emailAddress: email, firstName: firstName, lastName: lastName};
            axios
            .post("http://localhost:8080/api/v1/mailingList/register", userDetails)
            .then(result => {
                message = result.data;
                console.log(message)
            })
            .catch(error => console.log(error));
            
            return message;
        }
    }

    return (
        <Container>
            <h1>
                Registration Form
            </h1>
            <Form>
                <Row>
                    <Col>
                        <Form.Control 
                            placeholder="First name"
                            value={firstName}
                            onChange={e => setFirstName(e.target.value)} />
                    </Col>
                    <Col>
                        <Form.Control 
                            placeholder="Last name"
                            value={lastName}
                            onChange={e => setLastName(e.target.value)} />
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Form.Control 
                            placeholder="Email Address"
                            value={email}
                            onChange={e => setEmail(e.target.value)} />
                    </Col>
                </Row>
                <Row>
                    <Button size='lg' onClick={handleClick}>
                            Submit
                    </Button>
                </Row>
                
                
            </Form>
        </Container>
    )
}

export default MailingListRegistrationPage
