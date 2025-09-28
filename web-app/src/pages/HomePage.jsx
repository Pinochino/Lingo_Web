import React, { useContext, useEffect, useRef, useState } from 'react';
import AuthContext from '../contexts/AuthContext';
import { useDispatch } from 'react-redux';
import { loginGoogle } from '../slice/authentication';
import { toast } from 'react-toastify';

const HomePage = () => {
    const dispatch = useDispatch();
    const isProcessingRef = useRef(false);

    useEffect(() => {
        const params = new URLSearchParams(window.location.search);
        const code = params.get('code');

        if (code && !isProcessingRef.current) {
            isProcessingRef.current = true;

            window.history.replaceState({}, "", "/");

            (async () => {
                try {
                    await dispatch(loginGoogle(code)).unwrap();
                    toast.success("Đăng nhập thành công");
                } catch {
                    toast.error("Đăng nhập thất bại");
                } finally {
                    isProcessingRef.current = false;
                }
            })();
        }
    }, [dispatch]);

    return (
        <div>
        </div>
    );
};

export default HomePage;