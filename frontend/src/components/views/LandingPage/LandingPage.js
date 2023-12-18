import React, { useEffect } from 'react'
import axios from 'axios';
import { withRouter } from 'react-router-dom'; 
function LandingPage(props) {

    useEffect(() => {
        axios.get('/api/v1/sample')
            .then(response => { console.log(response) })
    }, [])


    const onClickHandler = () => {
        axios.get(`/api/v1/logout`)
            .then(response => {
                if (response.data.success) {
                    props.history.push("/v1/login")
                } else {
                    alert('로그아웃 하는데 실패 했습니다.')
                }
            })
    }

    return (
        <div>
            <h2>시작 페이지</h2>

            <button onClick={onClickHandler}>
                로그아웃
            </button>

        </div>
    )
}

export default withRouter(LandingPage)
