import React from 'react';

function MenuSection({ menuName, iconPath }) {
    return (
        <div className="MenuSection w-[100px] h-[100px] m-4">
            <div className="text-left">{menuName}</div>
            <img src={iconPath} alt="아이콘" className="float-right w-35 h-35" />
        </div>
    );
}

export default MenuSection;