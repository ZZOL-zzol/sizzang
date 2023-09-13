import React from 'react';

function PageTitle({ pageTitle }) {
    return (
        <div className="flex items-center w-[393px] h-[60px] p-2">
          <div className="text-left font-bold text-xl">&lt;</div>
          <div className="text-center font-bold flex-1">{pageTitle}</div>
        </div>
      );
}

export default PageTitle;