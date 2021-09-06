import React from 'react'
import { Container, Row, Col, Button, Form } from 'react-bootstrap';
import axios from 'axios'
import { useState } from 'react';

const MailingListUnsubscribePage = () => {
    const [email, setEmail] = useState("");

    const handleClick = () => {
        if (email.length === 0) {
            alert("Please enter an email address");
        } else {
            var message = "";
            setEmail(email.trim());

            axios
            .delete(`http://localhost:8080/api/v1/mailingList/delete/${email}`)
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
                Unsubcribe from the mailing list
            </h1>

            <Form>
                <Row>
                    <Form.Control 
                        placeholder="Email Address"
                        value={email}
                        onChange={e => setEmail(e.target.value)} />
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

export default MailingListUnsubscribePage
