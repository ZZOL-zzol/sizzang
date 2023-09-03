import React from 'react';

function PageTitle({ pageTitle }) {
    return (
        <div className="flex items-center w-[393px] h-[40px]">
          <div className="text-left font-bold">&lt;</div>
          <div className="text-center">{pageTitle}</div>
        </div>
      );
}

export default PageTitle;