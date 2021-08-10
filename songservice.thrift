namespace java com.example.gen
typedef i32 int

struct Song{
    1: int id
    2: string title
    3: list<string> singer
}

struct Like{
    1: int Songid
    2: int numLike
}

struct Listen{
    1: int Songid
    2: int numListen
}

struct ArtistListSongResponse{
    1: int code
    2: list<Song> listSong
}

struct SongResponse{
    1: int code
    2: Song song
}

enum ReturnCode {
    SUCCESS = 200,
    INVALID = 406,
    INTERNAL_SERVER_ERROR = 500
}

service SongService{

    SongResponse getSong(1: int id)
    ReturnCode removeSong(1: int id)
    ReturnCode updateSong(1: Song song)
    ReturnCode addSong(1: Song song)

    ReturnCode performLike(1: int songId)
    ReturnCode performUnlike(1: int songId)

    ReturnCode performIncreaseListen(1: int songId)

    ArtistListSongResponse getSongsByArtist(1: string artist)

    ArtistListSongResponse getTopSongBaseOnLike(1: int numbefOfTop)
    ArtistListSongResponse getTopSongBaseOnListen(1: int numberOfTop)
}