import React from 'react';

function MenuSection({ menuName, imageUrl }) {
    return (
        <div className="MenuSection flex flex-col justify-between bg-secondary-container rounded-2xl w-[100px] h-[100px] m-4 p-2">
            <div className="text-center font-bold">{menuName}</div>
            <img src={imageUrl} alt="아이콘" className="self-end rounded-full overflow-hidden w-[35px] h-[35px]" />
        </div>
    );
}

export default MenuSection;