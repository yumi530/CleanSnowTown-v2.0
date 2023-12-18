import React from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import Home from "./components/views/Common/Home";
import LoginPage from "./components/views/LoginPage/LoginPage";
import RegisterPage from "./components/views/RegisterPage/RegisterPage";
import Auth from "./hoc/auth";

import { Layout, Menu } from "antd";
const { Header, Footer, Content } = Layout;

const items = [
  { label: <Link to="/">홈</Link>, key: "home" },
  { label: <Link to="/order">배출신청</Link>, key: "order" },
  { label: <Link to="/pickup">수거요청</Link>, key: "pickup" },
  { label: <Link to="/qna">문의사항</Link>, key: "qna" },
  { label: <Link to="/login">로그인</Link>, key: "login" },
  { label: <Link to="/register">회원가입</Link>, key: "register" },
  { label: <Link to="/logout">로그아웃</Link>, key: "logout" },
];

function App() {
  return (
    <Router>
      <div>
        <Layout className="layout">
          <Header>
            <div className="logo" />
            <Menu theme="dark" mode="horizontal" items={items} />
          </Header>
          <Content>
            <div className="site-layout-content">
              <Switch>
                <Route exact path="/" component={Home} />
                <Route path="/login" component={LoginPage} />
                <Route path="/register" component={RegisterPage} />
                {/* <Route exact path="/" component={Auth(LandingPage, null )  } /> */}
                {/* <Route exact path="/login" component={Auth(LoginPage, false) } /> */}
                {/* <Route exact path="/register" component={Auth(RegisterPage, false)} /> */}
              </Switch>
            </div>
          </Content>
          <Footer style={{ textAlign: 'center' }}>스노우타운 2.0</Footer>
        </Layout>
      </div>
    </Router>
  );
}

export default App;
