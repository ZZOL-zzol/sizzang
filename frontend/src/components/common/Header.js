import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const Header = (props) => {
  const navigate = useNavigate();
  return (
    <div className="navbar bg-base-100">
      <div className="flex-1">
        {props.backButton ? (
          <Link to="/main">
            <button className="btn btn-ghost normal-case text-xl font-environmentR pr-1">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                height="1em"
                viewBox="0 0 320 512"
              >
                <path d="M9.4 233.4c-12.5 12.5-12.5 32.8 0 45.3l192 192c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L77.3 256 246.6 86.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0l-192 192z" />
              </svg>
            </button>
          </Link>
        ) : null}
        <div
          className={
            props.backButton
              ? "btn btn-ghost normal-case text-xl font-environmentR p-1 "
              : "btn btn-ghost normal-case text-xl font-environmentR"
          }
        >
          {props.title}
        </div>
      </div>
      {props.basketButton ? (
        <div className="flex-none">
            <button className="btn btn-square btn-ghost" onClick = {()=> navigate('/basket')}>
            <div className="indicator">
            <span className="indicator-item badge badge-secondary h-5 w-5 px-1 bg-myprimary border-none text-white">1</span>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                height="1.7em"
                viewBox="0 0 448 512"
              >
                <path d="M160 112c0-35.3 28.7-64 64-64s64 28.7 64 64v48H160V112zm-48 48H48c-26.5 0-48 21.5-48 48V416c0 53 43 96 96 96H352c53 0 96-43 96-96V208c0-26.5-21.5-48-48-48H336V112C336 50.1 285.9 0 224 0S112 50.1 112 112v48zm24 48a24 24 0 1 1 0 48 24 24 0 1 1 0-48zm152 24a24 24 0 1 1 48 0 24 24 0 1 1 -48 0z" />
              </svg>
              </div>
            </button>
        </div>
      ) : null}
    </div>
  );
};

export default Header;
