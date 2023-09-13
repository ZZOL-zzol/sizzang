import React from 'react';

function Button({btnText}) {
    return (
        <div>
            <button className="w-[350px] h-[40px] btn-primary rounded-2xl">{btnText}</button>
        </div>
    );
}

export default Button;