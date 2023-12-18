import React from "react";
import { Col, Row, Menu } from "antd";
import { Link } from "react-router-dom";

function NavBar() {
  return (
    <div>
      <div className="logo" />
      <Menu
        theme="dark"
        mode="horizontal"
        items={[
          { key: "home", label: "홈" },
          { key: "order", label: "배출신청" },
          { key: "pickup", label: "수거요청" },
          { key: "ask", label: "문의사항" },
          { key: "login", label: "로그인" },
          { key: "register", label: "회원가입" },
          { key: "logout", label: "로그아웃" },
        ]}
      >
        
        {/* <Menu.Item key="home">
          <Link to="/">홈</Link>
        </Menu.Item>
        <Menu.Item key="order">
          <Link to="/order">배출 신청</Link>
        </Menu.Item>
        <Menu.Item key="pickup">
          <Link to="/pickup">수거 요청</Link>
        </Menu.Item>
        <Menu.Item key="qna">
          <Link to="/qna">문의사항</Link>
        </Menu.Item> */}

        {/* <Menu.Item key="login">
          <Link to="/login">로그인</Link>
        </Menu.Item>
        <Menu.Item key="register">
          <Link to="/register">회원가입</Link>
        </Menu.Item>
        <Menu.Item key="logout">
          <Link to="/logout">로그아웃</Link>
        </Menu.Item> */}
      </Menu>
    </div>
  );
}

export default NavBar;
