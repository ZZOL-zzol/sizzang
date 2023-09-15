import React from 'react';

const MenuSection = ({ menuName, imageUrl, bgColor, onClickEvent }) => {
    return (
        <div className={`MenuSection flex flex-col justify-between ${bgColor} rounded-2xl w-[100px] h-[100px] p-2`} onClick={onClickEvent}>
            <div className="text-center font-bold">{menuName}</div>
            <img src={imageUrl} alt="아이콘" className="self-end rounded-full overflow-hidden w-[35px] h-[35px]" />
        </div>
    );
}

export default MenuSection;