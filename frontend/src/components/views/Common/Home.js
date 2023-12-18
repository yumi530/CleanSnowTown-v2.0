import React, { useEffect } from "react";
import axios from "axios";
import { withRouter } from "react-router-dom";
function LandingPage(props) {
  useEffect(() => {
    axios.get("/api/sample").then((response) => {
      console.log(response);
    });
  }, []);

  // const logoutHandler = () => {
  //     axios.get(`/api/v1/logout`)
  //         .then(response => {
  //             if (response.data.success) {
  //                 props.history.push("/v1/login")
  //             } else {
  //                 alert('로그아웃 하는데 실패 했습니다.')
  //             }
  //         })
  // }

  return (
    <div>
      <h2>스노우 타운에 오신 걸 환영합니다!</h2>
      <div>
        {/* <a href="/login">로그인</a> */}
        {/* <a href="/register">회원가입</a> */}
        {/* <button onClick={logoutHandler}>로그아웃</button> */}
      </div>
    </div>
  );
}

export default withRouter(LandingPage);
