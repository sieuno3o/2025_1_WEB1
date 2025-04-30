import './SearchBar.scss';

const SearchBar = () => {
	return (
		<div className="searchbar">
			<div className="searchbar-wrapper">
				<div className="searchbar-container">
					<img
						src="/assets/search-icon.png"
						alt="search icon"
						className="searchbar-icon"
					/>
					<input type="text" placeholder="검색" className="searchbar-input" />
				</div>
			</div>
		</div>
	);
};

export default SearchBar;
