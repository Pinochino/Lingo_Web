import React from 'react';
import { Link } from 'react-router-dom';

const HomePage = () => {
    return (
        <div>
            <Link to={"http://localhost:8180/realms/Lingo/protocol/openid-connect/auth?client_id=lingo-ui&redirect_uri=http%3A%2F%2Flocalhost%3A5173%2F&response_type=code&scope=openid%20email%20profile&kc_idp_hint=google"} >Click me</Link>
        </div>
    );
};

export default HomePage;