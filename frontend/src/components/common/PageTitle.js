import React from 'react';

function PageTitle({ pageTitle }) {
    return (
        <div className="flex items-center w-[393px] h-[40px]">
          <div className="text-left font-spoqa font-bold">&lt;</div>
          <div className="text-center font-spoqa">{pageTitle}</div>
        </div>
      );
}

export default PageTitle;