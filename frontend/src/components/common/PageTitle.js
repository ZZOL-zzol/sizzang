import React from 'react';

function PageTitle({ pageTitle }) {
    return (
        <div className="flex justify-between items-center w-393 h-40 bg-gray-800 text-white p-4">
          <div className="text-left font-bold">&lt;</div>
          <div className="text-center">{pageTitle}</div>
        </div>
      );
}

export default PageTitle;