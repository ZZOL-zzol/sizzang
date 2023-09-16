const SearchBar = (props) => {
  return (
    <div className="relative w-full h-[60px] px-5 py-auto mt-1">
      <input
        type="text"
        placeholder={props.placeholder}
        className="input input-bordered w-full border-myprimary h-10 rounded-full"
        value={props.keyword}
        onChange={props.onChangeEvent}
      ></input>
      <button
        type="submit"
        className="absolute right-3 top-[25%] flex bg-transparent px-2 rounded-md text-lg w-16 justify-center items-center"
        onClick={() => props.onClickEvent()}
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          height="1em"
          viewBox="0 0 512 512"
          className="fill-surface"
        >
          <path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z" />
        </svg>
      </button>
    </div>
  );
};

export default SearchBar;
