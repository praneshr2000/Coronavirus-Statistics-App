import 'bootstrap/dist/css/bootstrap.css';
import './App.css';
import { Button, Container, Col, Row } from 'react-bootstrap';


function App() {
  return(
    <Container className="homeContainer" className="d-flex flex-column min-vh-100 justify-content-center align-items-center">
      <Row className="appHeader">
        <h1>Welcome to the Coronavirus Statistcs and Daily Updates App!</h1>
      </Row>
      <Row >
        <Col className="d-grid gap-2" className='colClass'>
          <Button href="/countries/all" size="lg" className='button'>
            Global Coronavirus Statistics
          </Button>
        </Col>
        <Col className="d-grid gap-2" className='colClass'>
          <Button href='/mailing_list' size="lg" className='button'>
            Register for Daily Updates
          </Button>
        </Col>
      </Row>
    </Container>
  )
}

export default App;
