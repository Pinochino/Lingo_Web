import React, { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { retrieveAttempts } from '../slice/attempts';


const HomePage = () => {

    const dispatch = useDispatch();
    const { attempts } = useSelector(state => state.attempts);
    useEffect(() => {
        dispatch(retrieveAttempts(1));
    }, []);


    console.log(attempts);


    return (
        <div>
            hello
        </div>
    );
};

export default HomePage;