import 'bootstrap/dist/css/bootstrap.css';
import './App.css';
import { Button, Container, Col, Row } from 'react-bootstrap';


function App() {
  return(
    <Container className="homeContainer" className="d-flex flex-column min-vh-100 justify-content-center align-items-center">
      <Row className="appHeader">
        <h1>Welcome to the Coronavirus Statistics and Daily Updates App!</h1>
      </Row>

      <Row>
        <Col className="d-grid gap-2" className='colClass'>
          <Button href="/countries/all" size="lg" className='button'>
            Global Coronavirus Statistics
          </Button>
        </Col>
      </Row>

      <Row >
        <Col className="d-grid gap-2" className='colClass'>
          <Button href='/mailing_list/register' size="lg" className='button'>
            Register for Daily Coronavirus Updates
          </Button>
        </Col>
        <Col>
          <Button href="/mailing_list/unsubscribe" size="lg" className='button'>
            Unsubscribe from the Daily Updates
          </Button>
        </Col>
      </Row>
    </Container>
  )
}

export default App;
