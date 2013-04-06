package  semper.sil.testing

import java.io.File
import io.Source
import semper.sil.verifier.Verifier
import semper.sil.frontend.Frontend

/** A sil suite that can only handle single files. */
trait SingleFileSilSuite extends SilSuite {
  /** The frontend to be used. */
  def frontend(verifier: Verifier, files: Seq[File]): Frontend = files match {
    case file :: Nil => frontend(verifier, Source.fromFile(file).mkString)
    case _ => sys.error("This suite can only handle one file per test.")
  }

  /** The frontend to be used. */
  def frontend(verifier: Verifier, input: String): Frontend

  override def fileList(file: File) = Seq(file)

  override def fileListName(file: File) = file.getName
}